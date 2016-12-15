package nl.infosupport.proposalkeeper.services;

import nl.infosupport.proposalkeeper.models.Proposal;
import nl.infosupport.proposalkeeper.repositories.ProposalRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProposalServiceImplTests {
    private ProposalService proposalService;
    private ProposalRepository proposalRepository;

    @Before
    public void setUp() {
        proposalRepository = mock(ProposalRepository.class);
        proposalService = new ProposalServiceImpl(proposalRepository);
    }

    @Test
    public void findAllProposalsReturnsListOfProposals() {
        List<Proposal> proposals = Arrays.asList(
            new Proposal(1L, 1L, "Test session", "Test description"),
            new Proposal(2L, 1L, "Test session 2", "Test description")
        );

        when(proposalRepository.findAll()).thenReturn(proposals);

        Iterable<Proposal> retrievedProposals = proposalService.findAllProposals();

        assertThat(retrievedProposals, equalTo(proposals));
    }

    @Test
    public void findProposalsForUserReturnsListOfProposals() {
        List<Proposal> proposals = Arrays.asList(
            new Proposal(1L, 1L, "Test session", "Test description"),
            new Proposal(2L, 1L, "Test session 2", "Test description")
        );

        when(proposalRepository.findByUserId(1L)).thenReturn(proposals);

        Iterable<Proposal> retrievedProposals = proposalService.findProposalsForUser(1L);

        assertThat(retrievedProposals, equalTo(proposals));
    }

    @Test
    public void submitCallsSaveOnRepository() {
        proposalService.submit(1L, "Test", "Test");
        verify(proposalRepository).save(any(Proposal.class));
    }
}
